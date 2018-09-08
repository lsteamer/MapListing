package lsteamer.elmexicano.com.maplisting.mvp;

public interface Contract {

    interface ListViewContract{
        void setPresenter(Presenter presenter);

    }

    interface MapViewContract{
        void setPresenter(Presenter presenter);

    }

    interface PresenterContract {

    }
}
