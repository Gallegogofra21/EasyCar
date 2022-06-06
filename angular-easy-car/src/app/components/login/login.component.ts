import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthLoginDto } from 'src/app/models/dto/AuthLoginDto';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginDto = new AuthLoginDto();

  constructor(private authService: AuthService, private route: Router) { }

  ngOnInit(): void {
  }

  doLogin() {
    this.authService.login(this.loginDto).subscribe(loginResult => {
      this.authService.setLocalRequestToken(loginResult.token);
      this.route.navigate(['/home']);
    })
  }

}
