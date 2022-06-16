import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Concesionario, ConcesionarioDto, CreateConcesionarioDto } from 'src/app/models/concesionario-interface';
import { User, UserResponse } from 'src/app/models/user-interface';
import { ConcesionarioService } from 'src/app/services/concesionario.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-create-concesionario',
  templateUrl: './create-concesionario.component.html',
  styleUrls: ['./create-concesionario.component.css']
})
export class CreateConcesionarioComponent implements OnInit {

  file!: File;
  selectedUserId!: string;
  users!: User[];

  concesionarioDto = new CreateConcesionarioDto();

  concesionarioResponse !: Concesionario;

  form = new FormGroup({
    nombre: new FormControl(''),
    direccion: new FormControl(''),
    idGestor: new FormControl()
  })



  constructor(private concesionarioService: ConcesionarioService, private userService: UserService, private route: Router) { }

  ngOnInit(): void {
    this.getAllUsers();
  }


  doCreate() {
    this.concesionarioService.createConcesionario(this.concesionarioDto, this.selectedUserId).subscribe(res => {
      this.route.navigate(['/concesionarios']);
    })
  }

  getAllUsers(): void {
    this.userService.getUsers().subscribe(data => {
      this.users = data.content;
    })
  }

}
