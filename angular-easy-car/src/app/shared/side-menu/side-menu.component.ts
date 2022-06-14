import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user-interface';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.css']
})
export class SideMenuComponent implements OnInit {
  @Input() userInput!: User;

  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
    console.log(this.router.url);
  }

  getName() {
    return localStorage.getItem('name')? localStorage.getItem('name') : 'Login';
  }

  getPhoto() {
    return localStorage.getItem('foto');
  }

  logout() {
    localStorage.removeItem('request_token');
    localStorage.removeItem('name');
    localStorage.removeItem('foto');
  }
}
