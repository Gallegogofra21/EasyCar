import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RxFormBuilder } from '@rxweb/reactive-form-validators/services/rx-form-builder';
import { Tipo, TipoDto } from 'src/app/models/tipo-interface';
import { TipoService } from 'src/app/services/tipo.service';

export interface DialogData {
  tipo: Tipo;
  id: number;
}

@Component({
  selector: 'app-dialog-edit-tipo',
  templateUrl: './dialog-edit-tipo.component.html',
  styleUrls: ['./dialog-edit-tipo.component.css']
})
export class DialogEditTipoComponent implements OnInit {

  file!: File;


  formulario = new FormGroup({
    nombre: new FormControl(''),
    foto: new FormControl(''),
  });

  constructor(private tipoService: TipoService, @Inject(MAT_DIALOG_DATA) public data: DialogData) {
  }


  ngOnInit(): void {
    this.formulario.patchValue(this.data.tipo);
  }

  onFileChanged(event: any) {
    this.file = event.target.files[0];
  }

  tipo = new TipoDto();
  editTipo() {
    this.formulario.value
      this.tipoService.editTipo(this.formulario.value, this.data.id, this.file).subscribe(result => {
        history.go(0);
      })
    }




}
