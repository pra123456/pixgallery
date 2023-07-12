# Shutterfly Android Pixabay Repo

## App Info

A gallery app that retrieves images from external API and loads them in pages.
The App fetches images from Pixabay API based to the query search.
For API reference, please visit [Pixabay documentation](https://pixabay.com/api/docs/).

## General Instructions

You can use any 3rd party library (if needed - please choose those smartly) and any views are allowed.
Make sure the app doesn’t create too many network requests.
Try to make the code as efficient as possible (write fewer classes as possible)
Write in Kotlin.

## Schedule

Once received, try to **finish all tasks in 4 days** period.

## Features List

### Use Jetpack compose
convert the current UI using Jetpack compose use the link below as a guidline 
https://developer.android.com/jetpack/compose?gclid=CjwKCAjw3POhBhBQEiwAqTCuBgDHopPadv8RZI0uE9sEjsNblwMvkwUmUEJqmHJ--cNx38K8Rt2DzBoCqEYQAvD_BwE&gclsrc=aw.ds

### Support for landscape mode

Add support for landscape mode.
When rotating the screen, the grid should contain 4 columns instead of 3 (in portrait mode).

### Full Image Display

When tapping an image, navigate to a new screen that will present the image in full view.

### Favorites Support

Tapping on an image should mark it as favorite and adds an icon on top of the image. When tapping again on the same image, the image should be removed from favorites.
Pick any icon you'd like.
Favorites list should be cached locally.

### Image Likes

Add likes count to each image. The data can be retrieved as part of the Pixabay API.
Check the docs to understand to fetch it.

## How to Submit

1. To get started, copy this repo locally and create a new branch.
2. Add the above features and commit the changes.  Please use meaningful commits (organized, with messages explaining what you are doing).  Optional: create separate branches for each feature (based off of each other).
3. Create a new pull-request(s) from the created branch(es) to the main branch (NOTE: This requires you to send us your Github username so that we can add you as a collaborator.)
4. Send us a link to the PR you've created to <deliyahu@shutterfly.com>  As a less prefered fallback, you can zip up the project files and send this as an attachment/file as well if you have any trouble setting up a PR.

