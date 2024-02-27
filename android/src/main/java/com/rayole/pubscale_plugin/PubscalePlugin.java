package com.rayole.pubscale_plugin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;

import com.pubscale.sdkone.offerwall.OfferWall;
import com.pubscale.sdkone.offerwall.OfferWallConfig;
import com.pubscale.sdkone.offerwall.models.OfferWallInitListener;
import com.pubscale.sdkone.offerwall.models.OfferWallListener;
import com.pubscale.sdkone.offerwall.models.Reward;
import com.pubscale.sdkone.offerwall.models.errors.InitError;

import java.util.HashMap;
import java.util.Objects;

/** PubscalePlugin */
public class PubscalePlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private  Context context;

  private Activity activity;

  private MethodChannel channel;

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

  @Override
  public void onAttachedToActivity(@NonNull ActivityPluginBinding activityPluginBinding) {
    activity = activityPluginBinding.getActivity();
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {

  }

  @Override
  public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding activityPluginBinding) {

  }

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "pubscale_plugin");
    context = flutterPluginBinding.getApplicationContext();
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("initSDK")) {
      this.initSdk((HashMap) call.arguments);
      result.success(Boolean.TRUE);
    } else if (call.method.equals("showOfferwall")) {
      this.showOfferWall();
      result.success(Boolean.TRUE);
    } else {
      result.notImplemented();
    }
  }

  OfferWallListener offerWallListener = new OfferWallListener() {

    @Override
    public void onRewardClaimed(Reward reward) {

    }

    @Override
    public void onOfferWallShowed() {
      Log.d("PUBSCALE", "Offer wall showed.");
    }

    @Override
    public void onOfferWallClosed() {
      Log.d("PUBSCALE", "Offer wall closed.");
    }

    @Override
    public void onFailed(@NonNull String message) {
      Log.d("PUBSCALE", "onFailed: " + message);
    }
  };

  private void initSdk(final HashMap args) {
    String appId = Objects.requireNonNull(args.get("appId")).toString();
    String userId = Objects.requireNonNull(args.get("userId")).toString();
    Log.e("appId", appId);
    Log.e("userId", userId);
    OfferWallConfig offerWallConfig =
            new OfferWallConfig.Builder(context, appId)
                    .setUniqueId(userId)
                    .setFullscreenEnabled(true)
                    .build();
    OfferWall.init(offerWallConfig, new OfferWallInitListener() {
      @Override
      public void onInitFailed(InitError initError) {
        Log.e("ERROR", initError.toString());
      }

      @Override
      public void onInitSuccess() {
        Log.e("SUCCESS", "Pubscale Initialised");
      }
    });
  }

  private void showOfferWall() {
    OfferWall.launch(activity, offerWallListener);
  }

  @Override
  public void onDetachedFromActivity() {

  }
}
