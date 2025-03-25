import 'package:flutter/material.dart';
import 'package:pubscale_plugin/pubscale_plugin.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  final PubscalePlugin _plugin = PubscalePlugin.instance;

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: const Text('Plugin example app')),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            spacing: 25,
            children: [
              FilledButton(
                onPressed: () async => await _plugin.initSDK(
                  appId: '60560882',
                  userId: '1234567890',
                ),
                child: const Text('Init'),
              ),
              FilledButton(
                onPressed: () async => await _plugin.showOfferwall(),
                child: const Text('Show'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
