import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'pubscale_plugin_platform_interface.dart';

/// An implementation of [PubscalePluginPlatform] that uses method channels.
class MethodChannelPubscalePlugin extends PubscalePluginPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('pubscale_plugin');

  @override
  Future<void> initSDK(
      String appId,
      String userId,
      ) =>
      methodChannel.invokeMethod<bool>(
        'initSDK',
        {
          'appId': appId,
          'userId': userId,
        },
      );

  @override
  Future<void> showOfferWall() =>
      methodChannel.invokeMethod<bool>('showOfferwall', {});
}
