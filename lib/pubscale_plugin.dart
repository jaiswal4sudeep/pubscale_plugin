import 'pubscale_plugin_platform_interface.dart';

class PubscalePlugin {
  static PubscalePlugin? _instance;

  static PubscalePlugin get instance => _instance ??= PubscalePlugin();

  final PubscalePluginPlatform _platform = PubscalePluginPlatform.instance;

  Future<void> initSDK({
    required String appId,
    required String userId,
  }) async =>
      await _platform.initSDK(
        appId,
        userId,
      );

  Future<void> showOfferwall() async => await _platform.showOfferWall();
}
