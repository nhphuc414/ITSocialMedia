# ITSocialMedia

# Hệ thống mạng xã hội trực tuyến
## Hướng dẫn cài đặt
### Bước 1: Cài đặt môi trường
 #### Phía front-end
  - **Cài đặt môi trường NodeJS:** vào [trang chủ NodeJS](https://nodejs.org), tải về máy phiên bản phù hợp và cài đặt .

- **Cài đặt Visual Studio Code:** vào [trang chủ Visual Studio Code](https://code.visualstudio.com/download), và tải phiên bản phù hợp với máy tính.
#### Phía back-end
- **Cài đặt JDK 17:** tải và cài đặt JDK 17 [tại đây](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
- **Cài đặt MySQL Community Server:** tải và cài đặt MySQL Community Server [tại đây](https://dev.mysql.com/downloads/mysql/).
- **Cài đặt MySQL Workbench:** tải và cài đặt MySQL Workbench [tại đây](https://dev.mysql.com/downloads/workbench/).
- **Cài đặt IntelliJ IDEA:** tải và cài đặt IntelliJ IDEA [tại đây](https://www.jetbrains.com/idea/download/).
### Bước 2: Lấy mã nguồn
 Mở command line hoặc git bash và gõ lệnh sau để tải mã nguồn về máy tính.
 ```bash
git clone https://github.com/nhphuc414/ITSocialMedia.git
```
### Bước 3: Tạo Database
Mở **MySQL Workbench**,chọn connection, sau sau đó trên thanh công cụ,chọn server &#8594; Data Import &#8594; Import From Disk &#8594; Import from Self-Contained File, đường dẫn là file db.sql được clone từ mã nguồn, &#8594; Import Progress &#8594; Start Import

### Bước 4: Cấu hình 
 Vào file 
 ```bash
 ITSocialMedia\itsocialserver\src\main\resources\application.properties
 ```
 Chỉnh các thông số sau
 ```properties
spring.datasource.username=<Tên đăng nhập MySQL>
spring.datasource.password=<Mật khẩu đăng nhập MySQL>
spring.datasource.url = <Đường dẫn đến database> vd: jdbc:mysql://localhost:3306/itsocial?allowPublicKeyRetrieval=true&useSSL=false
```
```properties
spring.mail.username = <Email>
spring.mail.password= <Password email>
```
### Bước 5: Khởi chạy 
#### Phía back-end
mở thư mục itsocialserver bằng **Intellij IDEA**, chọn tập tin `ItsocialserverApplication`, chuột phải và nhấn Run. Ứng dụng Java sẽ được chạy trên `http://localhost:8080/`.
#### Phía front-end
mở thư mục itsocialclient bằng **Visual Code**,
mở terminal và thực thi lần lượt các lệnh sau

```bash
npm i
```

Sau khi thực thi thành công lệnh trên, thực thi tiếp lệnh sau để cài đặt tất cả các package cần thiết cho ứng dụng `ReactJS`:

```bash
yarn install
```

Cuối cùng thực thi lệnh sau để chạy ứng dụng:

```bash
yarn start
```

Ứng dụng sẽ chạy trên `http://localhost:3000`.

## Hướng dẫn sử dụng
Người dùng truy cập vào `http://localhost:3000` để hoặc đăng ký tài khoản mới hoặc đăng nhập bằng tài khoản đã có.
