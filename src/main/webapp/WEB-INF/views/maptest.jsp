<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>오시는 길</title>
  <style>
    body {
      font-family: 'Segoe UI', sans-serif;
      background-color: #f8f9fa;
      margin: 0;
      padding: 40px;
    }

    .location-section {
      max-width: 1100px;
      margin: 0 auto;
      background: #fff;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0,0,0,0.05);
      padding: 40px;
    }

    .location-title {
      font-size: 24px;
      font-weight: bold;
      color: #333;
      margin-bottom: 30px;
      border-left: 5px solid #007bff;
      padding-left: 15px;
    }

    .location-content {
      display: flex;
      flex-wrap: wrap;
      gap: 30px;
    }

    .location-info {
      flex: 1 1 350px;
      font-size: 16px;
      line-height: 1.8;
      color: #444;
    }

    .location-info h3 {
      font-size: 18px;
      margin-bottom: 10px;
      color: #007bff;
    }

    .map-box {
      flex: 1 1 500px;
      height: 500px;
      border: 1px solid #ddd;
      border-radius: 6px;
      overflow: hidden;
      background-color: #e9ecef;
      display: flex;
      align-items: center;
      justify-content: center;
      color: #777;
    }

    @media (max-width: 768px) {
      .location-content {
        flex-direction: column;
      }

      .map-box {
        height: 500px;
      }
    }
  </style>
</head>
<body>
	
  <section class="location-section">
    <div class="location-title">오시는 길</div>

    <div class="location-content">
      <!-- 회사 정보 -->
      <div class="location-info">
        <h3>주소</h3>
        서울특별시 마포구 신촌로 176, 중앙빌딩 4층<br>

        <h3>연락처</h3>
        전화: 02-1234-5678<br>
        이메일: contact@example.com<br>

        <h3>대중교통</h3>
        - 2호선 강남역 3번 출구 도보 5분<br>
        - 버스: 140, 146, 740번 승차 후 '강남역' 하차
      </div>

      <!-- 지도 자리 -->
      <div class="map-box">
      <div id="map" style="width:500px;height:400px;"></div>
      </div>
    </div>
  </section>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=de64eef9233af15c9a997b8bc9cb8b7a&libraries=services"></script>
	<script>
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(37.5564397859151, 126.945190775648),
			level: 3
		};

		var map = new kakao.maps.Map(container, options);
		
		//주소-좌표 변환 객체 생성
		var geocoder = new kakao.maps.services.Geocoder();
		
		var address = "서울특별시 마포구 신촌로 176, 중앙빌딩 ";
		
		geocoder.addressSearch(address, function(result, status) {
            if (status === kakao.maps.services.Status.OK) {
                // 검색된 좌표 (위도: y, 경도: x)
                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                // 지도 중심 이동
                map.setCenter(coords);

                // 마커 생성
                var marker = new kakao.maps.Marker({
                    map: map,
                    position: coords
                });

                // 인포윈도우 생성 (주소 표시)
                var infowindow = new kakao.maps.InfoWindow({
                    content: '<div style="padding:5px;">' + address + '</div>'
                });
                infowindow.open(map, marker);

                // 콘솔에 위도 경도 출력
                console.log("위도: " + result[0].y + ", 경도: " + result[0].x);
            } else {
                alert("주소 검색 실패: " + status);
            }
        });
	</script>
</body>
</html>
