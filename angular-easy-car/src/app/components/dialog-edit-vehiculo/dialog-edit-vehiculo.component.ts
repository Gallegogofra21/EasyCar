import { Component, Inject, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Marca } from 'src/app/models/marca-interface';
import { Vehiculo, VehiculoDto } from 'src/app/models/vehiculo-interface';
import { VehiculoService } from 'src/app/services/vehiculo.service';

export interface DialogData {
  vehiculo: Vehiculo;
  id: number;
}

@Component({
  selector: 'app-dialog-edit-vehiculo',
  templateUrl: './dialog-edit-vehiculo.component.html',
  styleUrls: ['./dialog-edit-vehiculo.component.css']
})
export class DialogEditVehiculoComponent implements OnInit {

  marcaList!: Marca[];

  file1!: File;
  file2!: File;
  file3!: File;
  file4!: File;

  formulario = new FormGroup({
    version: new FormControl(''),
    modelo: new FormControl(''),
    fechaMatriculacion: new FormControl(''),
    kilometraje: new FormControl(''),
    potencia: new FormControl(''),
    marchas: new FormControl(''),
    precio: new FormControl(''),
    llantas: new FormControl(''),
    distribucion: new FormControl(''),
    procedencia: new FormControl(''),
    traccion: new FormControl(''),
    foto1: new FormControl(''),
    foto2: new FormControl(''),
    foto3: new FormControl(''),
    foto4: new FormControl(''),
  });

  constructor(private vehiculoService: VehiculoService, @Inject(MAT_DIALOG_DATA) public data: DialogData) { }

  ngOnInit(): void {
    this.formulario.patchValue(this.data.vehiculo);
  }

  onFileChanged1(event: any) {
    this.file1 = event.target.files[0];
  }

  // onFileChanged2(event: any) {
  //   this.file2 = event.target.files[0];
  // }

  // onFileChanged3(event: any) {
  //   this.file3 = event.target.files[0];
  // }

  // onFileChanged4(event: any) {
  //   this.file4 = event.target.files[0];
  // }

  vehiculo = new VehiculoDto();

  editVehiculo() {
    this.vehiculoService.editVehiculo(this.formulario.value, this.data.id, this.file1).subscribe(result => {
      history.go(0);
    })
  }

}
