# MapListing

<br>

# Coder Notes
The Challenge uses an MVP pattern.<br>

<br>
<img src="/screenshots/1.png"/><br>
There in a Main Activity that gives  are two view fragments (ListViewLayer, MapViewLayer) to the presenter in the constructor and later, as we get them, gives the Location and the information as a List of a custom Object (CarData).
<br>
<br>
The data is downloaded with retrofit and there are two fragments on the same Activity. Ideally, the listView would handle clicks and depending the object clicked the mapView would reflect the changes.
This could be used in a tablet view of the app. 
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

Regardless of the result of this application, I hope I can get thorough comments regarding my code. I'm a self learner and constructive criticism is not only welcomed, but sought after, that way I can learn from my shortcomings.

Thank you for your time.
