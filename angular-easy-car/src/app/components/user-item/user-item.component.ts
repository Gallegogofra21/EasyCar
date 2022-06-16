import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { User } from 'src/app/models/user-interface';
import { UserService } from 'src/app/services/user.service';
import { DialogEditUserComponent } from '../dialog-edit-user/dialog-edit-user.component';

@Component({
  selector: 'app-user-item',
  templateUrl: './user-item.component.html',
  styleUrls: ['./user-item.component.css']
})
export class UserItemComponent implements OnInit {
  @Input() userInput!: User;
  id !: string;

  constructor(private userService: UserService, private dialog: MatDialog) { }

  ngOnInit(): void {
  }

  getDeleteUser(id : number | undefined) {
    if (id != null) {
      this.userService.deleteUser(id).subscribe(result => {
        this.userInput = result;
        window.location.reload();
      })
    }
  }

  editUser(user: User, idUser: number): void {
    this.dialog.open(DialogEditUserComponent, {
      data: {user: user, id: idUser},
      width: '500px',
      height: '500px',
    });
  }

}
