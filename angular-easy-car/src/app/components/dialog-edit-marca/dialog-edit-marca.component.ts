import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Marca, MarcaDto } from 'src/app/models/marca-interface';
import { MarcaService } from 'src/app/services/marca.service';

export interface DialogData {
  marca: Marca;
  id: number;
}

@Component({
  selector: 'app-dialog-edit-marca',
  templateUrl: './dialog-edit-marca.component.html',
  styleUrls: ['./dialog-edit-marca.component.css']
})
export class DialogEditMarcaComponent implements OnInit {

  file!: File;

  formulario = new FormGroup({
    nombre: new FormControl(''),
    foto: new FormControl(''),
  });

  constructor(private marcaService: MarcaService, @Inject(MAT_DIALOG_DATA) public data: DialogData) {
   }

  ngOnInit(): void {
    this.formulario.patchValue(this.data.marca);
  }
  onFileChanged(event: any) {
    this.file = event.target.files[0];
  }

  marca = new MarcaDto();

  editMarca() {
    this.marcaService.editMarca(this.formulario.value, this.data.id, this.file).subscribe(result => {
      history.go(0);
    })
  }

}
