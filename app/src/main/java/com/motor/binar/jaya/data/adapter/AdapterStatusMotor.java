package com.motor.binar.jaya.data.adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.motor.binar.jaya.R;
import com.motor.binar.jaya.data.model.Motor;
import com.motor.binar.jaya.ui.editmotor.EditMotorActivity;
import com.motor.binar.jaya.ui.main.MainAct;
import com.motor.binar.jaya.utils.ProgressBarAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.facebook.GraphRequest.TAG;

/**
 * Created by ikun on 08/02/18.
 */

public class AdapterStatusMotor extends Adapter<AdapterStatusMotor.ViewHolder> {

    private Context mcontext;
    private List<Motor> mitem;
    private MainAct activity;

    public AdapterStatusMotor(ArrayList<Motor> item, Context context, MainAct activity){
        this.mcontext = context;
        this.mitem = item;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.list_item_motor, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Motor motor = getItem(position);
        Log.e(TAG, "onBindViewHolder: "+motor);

        holder.txtplat.setText(motor.getSeri()+" "+motor.getPlat());
        holder.txtmerk.setText(motor.getMerk());


        float from = motor.getKm_NextService();
        float from1 = motor.getKm_now();
        float hasil = (from1/from)*100;
        Log.e(TAG, "onBindViewHolder: "+hasil);
        ProgressBarAnimation anim = new ProgressBarAnimation(holder.progresKilometer, hasil-2, hasil);
        anim.setDuration(1000);
        anim.setRepeatMode(ValueAnimator.RESTART);
        anim.setRepeatCount(ValueAnimator.INFINITE);
        anim.setInterpolator(new LinearInterpolator());
        holder.progresKilometer.setSecondaryProgress((int) hasil);
        holder.progresKilometer.startAnimation(anim);




        final GradientDrawable background = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{Color.BLUE, Color.RED, Color.BLUE, Color.RED});
        holder.bar.setBackground(background);
        holder.progresKilometer.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(final View v, final int left, final int top, final int right, final int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                background.setBounds(-2 * v.getWidth(), 0, v.getWidth(), v.getHeight());
                ValueAnimator animation = ValueAnimator.ofInt(0, 2 * v.getWidth());
                animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        background.setBounds(-2 * v.getWidth() + (int) animation.getAnimatedValue(), 0, v.getWidth() + (int) animation.getAnimatedValue(), v.getHeight());
                    }
                });
                animation.setRepeatMode(ValueAnimator.RESTART);
                animation.setInterpolator(new LinearInterpolator());
                animation.setRepeatCount(ValueAnimator.INFINITE);
                animation.setDuration(3000);
                animation.start();
            }
        });


        holder.btnUpdateKm.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(mcontext);
                View promptsView = li.inflate(R.layout.updatekm, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        mcontext);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.txtUpdatekm);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Update",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {

                                    motor.setKm_now(Integer.valueOf(userInput.getText().toString()));

                                        activity.updateKM(motor);
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });


    }



    @Override
    public int getItemCount() {
        return mitem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

        @Bind(R.id.txtPlat)
        TextView txtplat;
        @Bind(R.id.txtMerk)
        TextView txtmerk;
        @Bind(R.id.progresKilometer)
        ProgressBar progresKilometer;
        @Bind(R.id.progress)
        View bar;
        @Bind(R.id.btnUpdateKm)
        Button btnUpdateKm;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.setIsRecyclable(false);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
           Motor motor = getItem(this.getAdapterPosition());
            Log.e(TAG, "onClick: "+motor );
            EditMotorActivity.startWithMotor(activity,motor);
        }
    }

    private Motor getData(int adptPosition){
        return mitem.get(adptPosition);
    }

    @Nullable
    public Motor getItem(int position) {
        return mitem.get(position);
    }

    public void UpdateMotor(List<Motor> listarray) {
        mitem = listarray;
        notifyDataSetChanged();
    }
}
