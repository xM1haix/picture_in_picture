import "package:flutter/material.dart";
import "package:flutter/services.dart";
import "package:permission_handler/permission_handler.dart";

void main() async {
  await Permission.storage.request();
  await Permission.systemAlertWindow.request();
  runApp(const MyApp());
}

///Main skelethon of the app
class MyApp extends StatelessWidget {
  ///
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData.dark(),
      home: const Page(),
    );
  }
}

///Main skelethon of the app
class Page extends StatefulWidget {
  ///
  const Page({super.key});

  @override
  State<Page> createState() => _PageState();
}

///Object which calls the [MethodChannel] to to capture the screen
class ScreenCapture {
  ///
  const ScreenCapture(this.x);

  /// The [MethodChannel]
  static const platform = MethodChannel("com.example/screen_capture");

  /// IDK what was this for
  final int x;

  ///[Future] which calls 'startCapture' on [platform]
  static Future<void> startCapture() async {
    try {
      await platform.invokeMethod("startCapture");
    } on PlatformException catch (e) {
      debugPrint("Failed to start screen capture: '${e.message}'.");
    }
  }
}

class _PageState extends State<Page> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(),
      floatingActionButton: FloatingActionButton(
        onPressed: () async {
          await ScreenCapture.startCapture();
        },
      ),
    );
  }
}
