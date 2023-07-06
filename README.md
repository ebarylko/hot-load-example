# hot-load


## Overview

This project is to demonstrate an issue with the hot reload feature. Right now, if you make a change to the home page and save, the changes should be seen automatically. However, If you go to the routes file and change the value of view in router-component to be (-> current-route :data :view), the hot reload feature will no longer work. Even though both values return the same view, the hot reload feature only works in the first case.

## Running the project

* Install shadow-cljs by running `npm install shadow-cljs`
* Run `npx shadow-cljs watch app`
* When the app compiles, go to localhost:8200 
