import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { User, UserDto } from 'src/app/models/user-interface';
import { UserService } from 'src/app/services/user.service';

export interface DialogData {
  user: User;
  id: number;
}

@Component({
  selector: 'app-dialog-edit-user',
  templateUrl: './dialog-edit-user.component.html',
  styleUrls: ['./dialog-edit-user.component.css']
})
export class DialogEditUserComponent implements OnInit {

  file!: File;

  formulario = new FormGroup({
    username: new FormControl(''),
    nombre: new FormControl(''),
    apellidos: new FormControl(''),
    telefono: new FormControl(''),
    email: new FormControl(''),
    avatar: new FormControl(''),
  });

  constructor(private userService: UserService, @Inject(MAT_DIALOG_DATA) public data: DialogData) { }

  ngOnInit(): void {
    this.formulario.patchValue(this.data.user);
  }

  onFileChanged(event: any) {
    this.file = event.target.files[0];
  }

  user = new UserDto();

  editUser() {
    this.userService.editGestor(this.formulario.value, this.data.id, this.file).subscribe(result => {
      history.go(0);
    })
  }

}
