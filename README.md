## Login form UI exercise

This is a very simple **Kotlin + Jetpack Compose** user interface of login form for Android app.

### Description
This project implements a basic login screen layout with:
- Username (email) input field
- Password input field with hidden characters
- Submit button

The project doesn't implement proper authentication, validation or backend logic. \
Pressing submit-button doesn't perform real login operation or redirect to another view.

### Features
- `Column` layout to arrange elements vertically
- `OutlinedTextField` components with trailing icons (default mail and lock icons)
- Proper keyboard types for email and password\
  (KeyboardOptions -> KeyboardType.Email and KeyboardType.Password)
- Password masking using `PasswordVisualTransformation`
- Styling uses modifiers (padding, width, colors, borders)

### Screenshots

<table>
<tr>
<td><img src="img.png" width="300"/></td>
<td><img src="img_1.png" width="300"/></td>
</tr>
</table>

### Running the app
This project was created using Android Studio.
To clone and open the project:
```
- git clone https://github.com/Anniina-55/Login-form.git
- cd login-form
- open the project in Android Studio

-> UI should render on emulator and physical devices
