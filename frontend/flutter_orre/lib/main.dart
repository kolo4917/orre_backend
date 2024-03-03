import 'dart:convert';
import 'package:web_socket_channel/web_socket_channel.dart';

// 웨이팅 인포 클래스 선언
class WaitingInfo {
  final String serverCode;
  final String myName;
  final String phoneNumber;
  final int numberOfUs;

  WaitingInfo({
    required this.serverCode,
    required this.myName,
    required this.phoneNumber,
    required this.numberOfUs,
  });

  // JSON에서 Dart 객체 생성자
  factory WaitingInfo.fromJson(Map<String, dynamic> json) {
    return WaitingInfo(
      serverCode: json['serverCode'],
      myName: json['myName'],
      phoneNumber: json['phoneNumber'],
      numberOfUs: json['numberOfUs'],
    );
  }
}

// 웨이팅 타임 클래스 선언
class Waitingtime {
  final String serverCode2;
  final int lastWaitingNumber;
  final int predictWaitingTime;

  Waitingtime({
    required this.serverCode2,
    required this.lastWaitingNumber,
    required this.predictWaitingTime,
  });

  // JSON에서 Dart 객체 생성자
  factory Waitingtime.fromJson(Map<String, dynamic> json) {
    return Waitingtime(
      serverCode2: json['serverCode'],
      lastWaitingNumber: json['lastWaitingNumber'],
      predictWaitingTime: json['predictWaitingTime'],
    );
  }
}

void main() {
  final channel = WebSocketChannel.connect(
    Uri.parse('ws://192.168.0.13:8080/ws'),
  );

  // 서버에 연결 요청
  channel.sink.add('CONNECT\naccept-version:1.1,1.0\n\n\x00');

  // 서버로부터 메시지 수신 대기
  channel.stream.listen((data) {
    print('Received: $data');
    if (data.contains('CONNECTED')) {
      print('Connected to the server.');
      // 연결 성공 후, /topic/waitingInfo 주제에 구독
      channel.sink.add('SUBSCRIBE\nid:sub-0\ndestination:/topic/waitingInfo\n\n\x00'); // POST 요청하고 GET 할거 선언
      // 연결 성공 후 즉시 /waitingInfoRequest 요청을 보내야 함
      channel.sink.add('SEND\ndestination:/app/waitingInfoRequest\n\n\x00'); // POST 요청과 동일하다고 보면 됨
    } else if (data.contains('MESSAGE')) {
      //topic/waitingInfo로부터 메시지 수신 확인
      if (data.contains('destination:/topic/waitingInfo')) {
        final payloadStart = data.indexOf('\n\n') + 2; // STOMP 프로토콜은 헤더와 본문을 두 줄 띄어쓰기로 구분함
        var payload = data.substring(payloadStart).trim();
        // 문자 제거 안해주면 계속 예외뜸... 
        payload = payload.replaceAll('\x00', '');
        try {
          // 수신된 메시지 처리 (예: JSON 파싱)
          final messageData = json.decode(payload);
          // JSON 문자열을 WaitingInfo 객체로 변환
          final waitingInfo = WaitingInfo.fromJson(messageData);
          // 객체 필드에 접근하여 출력
          print('Server Code: ${waitingInfo.serverCode}');
          print('My Name: ${waitingInfo.myName}');
          print('Phone Number: ${waitingInfo.phoneNumber}');
          print('Number of Us: ${waitingInfo.numberOfUs}');
        } catch (e) {
          print('Error parsing JSON: $e');
          print('Original payload: $payload');
        }
      }
    }
    // WaitingTime 정보 요청 및 처리
    if (data.contains('CONNECTED')) {
      print('Connected to the server.');
      // 연결 성공 후, /topic/waitingTimeInfo 주제에 구독
      channel.sink.add('SUBSCRIBE\nid:sub-1\ndestination:/topic/waitingTimeInfo\n\n\x00');
      // 연결 성공 후 즉시 /waitingTimeInfoRequest 요청을 보내야 함
      channel.sink.add('SEND\ndestination:/app/waitingTimeInfoRequest\n\n\x00');
    } else if (data.contains('MESSAGE')) {
      //topic/waitingTimeInfo로부터 메시지 수신 확인
      if (data.contains('destination:/topic/waitingTimeInfo')) {
        final payloadStart = data.indexOf('\n\n') + 2;
        var payload = data.substring(payloadStart).trim();
        payload = payload.replaceAll('\x00', '');
        try {
          // 수신된 메시지 처리 (예: JSON 파싱)
          final messageData = json.decode(payload);
          // JSON 문자열을 Waitingtime 객체로 변환
          final waitingTime = Waitingtime.fromJson(messageData);
          // 객체 필드에 접근하여 출력
          print('Server Code 2: ${waitingTime.serverCode2}');
          print('Last Waiting Number: ${waitingTime.lastWaitingNumber}');
          print('Predict Waiting Time: ${waitingTime.predictWaitingTime}');
        } catch (e) {
          print('Error parsing JSON: $e');
          print('Original payload: $payload');
        }
      }
    }
  });
}
