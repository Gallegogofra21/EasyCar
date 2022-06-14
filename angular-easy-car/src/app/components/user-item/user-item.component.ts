import { Component, Input, OnInit } from '@angular/core';
import { User } from 'src/app/models/user-interface';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-item',
  templateUrl: './user-item.component.html',
  styleUrls: ['./user-item.component.css']
})
export class UserItemComponent implements OnInit {
  @Input() userInput!: User;
  id !: string;

  constructor(private userService: UserService) { }

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

}
