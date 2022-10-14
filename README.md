# jetpackcomposeproofofconcept

For this project I used Clean architecture in the way showed in this graphic: 

![Screenshot_2](https://user-images.githubusercontent.com/56835908/195689402-4f8b6490-83a2-428a-81a7-6dbbd07aa9d2.png)

The idea is the following:

- Presentation: All composables, navigation and screens. Here I'm using state to update the UI when changes are applied. I'm using events to control the user interaction with UI and the the view model is in charge to comunicate with the domain layer.
- Domain: All the business logic. Use cases and repository interfaces that will be implemented by the data layer.
- Data: All the models and repostiory interfaces.

So this could be the idea: 

![image](https://user-images.githubusercontent.com/56835908/195690759-92255357-ab70-4052-b769-8a67d3f8895e.png)

I also added a package called di whith the module with all the provides need using Hilt.

Third party libraries used: 
  - Retrotif: for getting the info from the api. https://square.github.io/retrofit/
  - Gson: for transforming the data retrieved from server into objects. https://github.com/google/gson
  - Timber: For writting logs. https://github.com/JakeWharton/timber.
    
I also used corrutines for doing the requests, room for storing the data in the device and compose navigation for the whole app navigation.