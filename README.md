# 🏪 오리 가오리 - 원격 웨이팅 및 주문 관리 시스템 

![Logo](https://github.com/user-attachments/assets/e8c467df-b7a5-44b1-b0c6-f12dd566a835)

## 📌 프로젝트 소개  
**오리 가오리**는 가게의 웨이팅과 주문을 원격으로 관리할 수 있도록 도와주는 서비스입니다.  
사용자는 매장 방문 전 **웨이팅 인원을 확인하고 예약**할 수 있으며, **QR/NFC를 활용하여 비회원도 웨이팅**이 가능합니다.  
매장 점주는 **앱을 통해 웨이팅과 주문을 실시간으로 관리**할 수 있어 운영 효율을 극대화할 수 있습니다.  

---

## 🎯 주요 기능  
![Functions](https://github.com/user-attachments/assets/61309f4f-a3f5-4036-84a2-e9ffb6d6581b)

### 🔹 1. 원격 웨이팅 확인 및 예약  
✅ 사용자는 앱에서 대기 인원을 확인하고 사전 웨이팅을 등록할 수 있습니다.  

### 🔹 2. NFC/QR을 통한 비회원 웨이팅  
✅ 회원이 아니더라도 매장 앞에서 **QR 코드 또는 NFC 태그**를 이용해 웨이팅을 등록할 수 있습니다.  

### 🔹 3. NFC/QR을 통한 비대면 주문  
✅ 테이블마다 부착된 **QR 코드/NFC 태그를 스캔하여 비대면 주문**을 진행할 수 있습니다.  
✅ 인력 부담을 줄이고 운영 효율을 높일 수 있습니다.  

### 🔹 4. 점주를 위한 관리 앱 제공  
✅ 점주는 **웨이팅 및 주문을 한 곳에서 통합 관리**할 수 있습니다.  
✅ 실시간으로 변동되는 데이터를 반영하여 손쉽게 매장을 운영할 수 있습니다.  

---

## 🏗 시스템 아키텍처  
![System Architecture - BACKEND](https://github.com/user-attachments/assets/5a79d2ee-45c4-415b-8dfb-4d23ad1c0980)
![System Architecture - ORRE](https://github.com/user-attachments/assets/19611b2d-a95b-40f4-9fd9-4f4a263facd6)
![System Architecture - GAORRE](https://github.com/user-attachments/assets/25fc2975-23a8-4b5a-8040-ff7302c7093d)

---

## 📺 소개 영상  
🔗 [원격 웨이팅 앱 오리, 가오리 시연 영상](https://www.youtube.com/watch?v=tMEdkNkiJkg)  

## 🚀 기술 스택  

### **Frontend**  
- ✅ React Native (앱)  
- ✅ HTML, CSS, JavaScript  

### **Backend**    
- ✅ Spring boot  
- ✅ MySQL  

### **Infrastructure**  
- ✅ AWS (EC2, S3, CloudWatch)  
- ✅ Nginx (Reverse Proxy & Load Balancer)  

---

## 🛠 주요 문제 해결 (Error Shooting)  

### 🔥 1. 실시간 데이터 반영 문제  
✔️ 웨이팅 정보가 실시간으로 반영되도록 하기 위해 **폴링, CDC(Change Data Capture), 외부 큐 시스템** 등을 비교하여 최적화된 방안을 선택  
✔️ **애플리케이션 레벨 트리거 기법**을 활용하여 **실시간 반영 속도 개선**  

### 🔥 2. 웹소켓 무한 연결 요청 문제  
✔️ **AWS에서 CPU 사용률이 100%까지 치솟아 서버 다운 현상 발생**  
✔️ 웹소켓 연결을 **필요한 기능에만 최적화**하고, **동일 유저가 1초에 1번만 연결 요청 가능하도록 제한**  

## 실제 매장 사용 모습  

![sundobu](https://github.com/user-attachments/assets/7044bfa4-729e-427e-a58c-1ac33fbaa013)
![gompocha](https://github.com/user-attachments/assets/20409b38-2d07-483e-bc31-2a792ed335c7)



