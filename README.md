# MapListing

<br>

# Coder Notes
The Challenge uses an MVP pattern.<br>

<br>
<img src="/screenshots/1.png"/><br>
There in a Main Activity that gives two view fragments (ListViewLayer, MapViewLayer) when initializing the presenter. The main Activity also gives as we get them, the Location and the downloaded Data as a List of custom Objects (CarData).
<br>
<br>
The data is downloaded with retrofit and there are two fragments on the same Activity. This way: the ListViewLayer can handle clicks and depending the object clicked the MapViewLayer can reflect the changes and the other way around. This was not implemented but it was left open to expnsion, as it could be used in a tablet view of the app. 
<br><br><br>
This structure was adapted from the [Android Architecture Blueprints]<br>
(https://github.com/googlesamples/android-architecture/tree/todo-mvp) <br>

# Dependencies
It used Retrofit (and it's gson parser), Butterknife and maps dependencies for location and map services.
<img src="/screenshots/2.png"/>
<br>
<br>

# Notes on the Challenge
The challenge uses MVP without a clear cut between the presenter and the Main Activity. I decided to have the permission request, obtain the location and the retrofit call in the Main Activity to feed everything to the Presenter.

I also tried to have the two fragments talking to one Presenter, this way actions done on one Fragment would impact the other. It was not requested in the challenge details, but I left the app open to expand on those aspects

I tried to have the object creation either in the Main Activity or in the Utils class, as eventually I would like to implement some sort of Dependecy Injection framework (such as Dagger 2). The project also lacks Unit testing due to lack of time.

Regardless of the result of this application, I hope I can get thorough comments regarding my code. I'm a self learner and constructive criticism is not only welcomed, but sought after, that way I can become a better programmer.

Thank you for your time.
