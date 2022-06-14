import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Concesionario, ConcesionarioDto } from 'src/app/models/concesionario-interface';
import { ConcesionarioService } from 'src/app/services/concesionario.service';

export interface DialogData {
  concesionario: Concesionario;
  id: number;
}

@Component({
  selector: 'app-dialog-edit-concesionario',
  templateUrl: './dialog-edit-concesionario.component.html',
  styleUrls: ['./dialog-edit-concesionario.component.css']
})
export class DialogEditConcesionarioComponent implements OnInit {

  formulario = new FormGroup({
    nombre: new FormControl(''),
    direccion: new FormControl(''),
  });

  constructor(private concesionarioService: ConcesionarioService, @Inject(MAT_DIALOG_DATA) public data: DialogData) {

   }

  ngOnInit(): void {
    this.formulario.patchValue(this.data.concesionario);
  }

  concesionario = new ConcesionarioDto();

  editConcesionario() {
    this.concesionarioService.editConcesionario(this.formulario.value, this.data.id).subscribe(result => {
      history.go(0);
    })
  }

}
