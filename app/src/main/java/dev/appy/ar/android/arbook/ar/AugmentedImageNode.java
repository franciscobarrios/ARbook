/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.appy.ar.android.arbook.ar;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.google.ar.core.AugmentedImage;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;

import java.util.concurrent.CompletableFuture;

/**
 * Node for rendering an augmented image. The image is framed by placing the virtual picture frame
 * at the corners of the augmented image trackable.
 */
@SuppressWarnings({"AndroidApiChecker"})
public class AugmentedImageNode extends AnchorNode {

    private static final String TAG = AugmentedImageNode.class.getCanonicalName();
    private static CompletableFuture<ModelRenderable> raven;
    private AugmentedImage image;
    private Node model;

    public AugmentedImageNode(Context context) {
        // Upon construction, start loading the models for the corners of the frame.
        if (raven == null) {
            raven = ModelRenderable.builder()
                    .setSource(
                            context,
                            Uri.parse("models/raven.sfb")
                    )
                    .build()
                    .exceptionally(throwable -> {
                        Toast toast =
                                Toast.makeText(context, "Unable to load renderable", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        return null;
                    });
        }
    }

    public AugmentedImage getImage() {
        return image;
    }

    /**
     * Called when the AugmentedImage is detected and should be rendered. A Sceneform node tree is
     * created based on an Anchor created from the image. The corners are then positioned based on the
     * extents of the image. There is no need to worry about world coordinates since everything is
     * relative to the center of the image, which is the parent node of the corners.
     */
    @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
    public void setImage(AugmentedImage image) {

        this.image = image;

        // If any of the models are not loaded, then recurse when all are loaded.
        if (!raven.isDone()) {
            CompletableFuture.allOf(raven)
                    .thenAccept((Void aVoid) -> setImage(image))
                    .exceptionally(
                            throwable -> {
                                Log.e(TAG, "Exception loading", throwable);
                                return null;
                            });
        }

        // Set the anchor based on the center of the image.
        setAnchor(image.createAnchor(image.getCenterPose()));

        Vector3 localPosition = new Vector3();
        localPosition.set(0f, 0f, 0f);

        model = new Node();
        Quaternion quaternion1 = model.getLocalRotation();
        Quaternion quaternion2 = Quaternion.axisAngle(new Vector3(0f, 180f, 0f), 20f);
        model.setLocalRotation(Quaternion.multiply(quaternion2, quaternion1));
        model.setLocalPosition(localPosition);
        model.setParent(this);
        model.setRenderable(raven.getNow(null));
    }
}
