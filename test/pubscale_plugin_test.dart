import 'package:flutter_test/flutter_test.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';
import 'package:pubscale_plugin/pubscale_plugin_method_channel.dart';
import 'package:pubscale_plugin/pubscale_plugin_platform_interface.dart';

class MockPubscalePluginPlatform
    with MockPlatformInterfaceMixin
    implements PubscalePluginPlatform {
      
  @override
  Future<void> initSDK(String appId, String userId) {
    throw UnimplementedError();
  }

  @override
  Future<void> showOfferWall() {
    throw UnimplementedError();
  }
}

void main() {
  final PubscalePluginPlatform initialPlatform =
      PubscalePluginPlatform.instance;

  test('$MethodChannelPubscalePlugin is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelPubscalePlugin>());
  });

  // test('getPlatformVersion', () async {
  //   PubscalePlugin pubscalePlugin = PubscalePlugin();
  //   MockPubscalePluginPlatform fakePlatform = MockPubscalePluginPlatform();
  //   PubscalePluginPlatform.instance = fakePlatform;
  //
  //   expect(await pubscalePlugin.getPlatformVersion(), '42');
  // });
}
