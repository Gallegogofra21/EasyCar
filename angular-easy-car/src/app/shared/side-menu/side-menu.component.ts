import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.css']
})
export class SideMenuComponent implements OnInit {

  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
    console.log(this.router.url);
  }

  getName() {
    return localStorage.getItem('name')? localStorage.getItem('name') : 'Login';
  }

  getPhoto() {
    return localStorage.getItem('photo');
  }

  logout() {
    localStorage.removeItem('request_token');
  }
}
