# CS377-Final-Project

## Overview
An art mueseum app that allows users to explore artworks from the Art Institute of Chicago API. They can search for artworks, view details with a simple click, and save artworks to a local favorite list.

## API
The API for the Art Institute of Chicago enables access to detailed information about all of the artworks in the institute’s collection such as artist, title, medium, date, and so on, as well as links to images of each art piece. Additionally, this API has a search endpoint which provides search-and-filter functionality, returning a list of artworks whose metadata contain any mention of the search term used, whether you’re searching by artist, title, subject matter, or any other applicable quality.

## Functionality
Our core functionality includes loading artworks into a RecyclerView with a Retrofit request from the API, searching by keyword, opening a new Fragment with detailed information for a chosen artwork (with ViewModels managing and exposing data to the Fragment(s)), saving favorite artworks locally with Room, and a drawer menu to switch between the main artwork list and the favorites page.

## Installation Requirements
To install and run this app, clone this repository and open it through Android Studio. Make sure you have a stable internet connection and at least the Panda 3 version of Android Studio.

## Architecture and Project Structure
This application follows the Model-View-ViewModel architectural pattern. Classes are divided between five different modules - data, which stores the information displayed in the app; network, which fetches data from the API; repository, which interfaces between the data, network, and viewmodel classes; viewmodel, which handles the business logic of the application; and ui, which displays information in the app's view components.

## Contribution Guidelines
