import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user-interface';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  userList!: User[];
  paginas!: number;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers() {
    this.userService.getUsers().subscribe(res => {
      this.userList = res.content;
      this.paginas = res.totalPages;
    })
  }

}
