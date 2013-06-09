package car.io.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import car.io.R;
import car.io.adapter.DbAdapter;
import car.io.application.ECApplication;
import car.io.views.RoundProgress;
import car.io.views.TYPEFACE;

import com.actionbarsherlock.app.SherlockFragment;

public class DashboardFragment extends SherlockFragment {

	TextView speedTextView;
	RoundProgress roundProgressSpeed;
	TextView co2TextView;
	RatingBar drivingStyle;
	RoundProgress roundProgressCO2;
	ImageView image;
	DbAdapter dbAdapter;
	ECApplication application;
	int speed;
	int speedProgress;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.dashboard, container, false);

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

		super.onViewCreated(view, savedInstanceState);

		application = ((ECApplication) getActivity().getApplication());

		dbAdapter = ((ECApplication) getActivity().getApplication())
				.getDbAdapterLocal();

		co2TextView = (TextView) getView().findViewById(R.id.co2TextView);
		speedTextView = (TextView) getView().findViewById(
				R.id.textViewSpeedDashboard);

		roundProgressCO2 = (RoundProgress) getView().findViewById(
				R.id.blue_progress_bar);
		roundProgressSpeed = (RoundProgress) getView().findViewById(
				R.id.blue_progress_bar2);

		drivingStyle = (RatingBar) getView().findViewById(R.id.ratingBar1);

		image = (ImageView) getView().findViewById(R.id.imageView1);

		// Handle the UI updates

		final Handler handler = new Handler();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {

				// Deal with the speed values

				speed = application.getSpeedMeasurement();
				speedTextView.setText(speed + " km/h");
				if (speed <= 0)
					speedProgress = 0;
				else if (speed > 200)
					speedProgress = 100;
				else
					speedProgress = speed / 2;
				roundProgressSpeed.setProgress(speedProgress);

				// TODO extend to co2 emissions

				// Repeat this in x ms
				handler.postDelayed(this, 1000);
			}
		};
		handler.postDelayed(runnable, 1000);

		TYPEFACE.applyCustomFont((ViewGroup) view,
				TYPEFACE.Newscycle(getActivity()));

	}

}
