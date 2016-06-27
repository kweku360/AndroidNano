package kfive.com.androidnano.RxAndroid;

import android.database.Observable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kfive.com.androidnano.R;
import rx.Observer;
import rx.Subscription;

public class RxTest extends AppCompatActivity {

    Button rxemit;
    TextView rxtxt;
    Observer<String> myObserver;
    rx.Observable<String> myFirstObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_test);

        rxemit = (Button)findViewById(R.id.rxemitbtn);
        rxtxt = (TextView)findViewById(R.id.rxval);

        viewMethods();
    }

    private void viewMethods() {
        myFirstObservable = rx.Observable.just("kankam");
        myObserver = new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                rxtxt.setText(s);
            }
        };
        rxemit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Subscription mysub = myFirstObservable.subscribe(myObserver);
            }
        });

    }
}
