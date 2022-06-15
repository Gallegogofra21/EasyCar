import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Concesionario } from 'src/app/models/concesionario-interface';
import { Marca } from 'src/app/models/marca-interface';
import { Tipo } from 'src/app/models/tipo-interface';
import { CreateVehiculoDto, Vehiculo } from 'src/app/models/vehiculo-interface';
import { ConcesionarioService } from 'src/app/services/concesionario.service';
import { MarcaService } from 'src/app/services/marca.service';
import { TipoService } from 'src/app/services/tipo.service';
import { VehiculoService } from 'src/app/services/vehiculo.service';

@Component({
  selector: 'app-create-vehiculo',
  templateUrl: './create-vehiculo.component.html',
  styleUrls: ['./create-vehiculo.component.css']
})
export class CreateVehiculoComponent implements OnInit {

  file!: File;
  selectedTipoId!: any;
  tipos!: Tipo[];

  selectedMarcaId!: any;
  marcas!: Marca[];

  selectedConcesionarioId!: any;
  concesionario!: Concesionario[];

  vehiculoDto = new CreateVehiculoDto();

  vehiculoResponse !: Vehiculo;

  form = new FormGroup({
    version: new FormControl(''),
    modelo: new FormControl(''),
    fechaMatriculacion: new FormControl(''),
    kilometraje: new FormControl(''),
    potencia: new FormControl(''),
    marchas: new FormControl(''),
    precio: new FormControl(''),
    nombreMarca: new FormControl(''),
    tipo: new FormControl(),
    llantas: new FormControl(''),
    distribucion: new FormControl(''),
    procedencia: new FormControl(''),
    traccion: new FormControl(''),
    foto1: new FormControl(''),
    foto2: new FormControl(''),
    foto3: new FormControl(''),
    foto4: new FormControl(''),
    concesionario: new FormControl(),
  })

  constructor(private vehiculoService: VehiculoService,
     private tipoService: TipoService,
     private marcaService: MarcaService,
     private concesionarioService: ConcesionarioService,
     private route: Router) { }

  ngOnInit(): void {
  }

  doCreate() {
    this.vehiculoService.createVehiculo(this.vehiculoDto, this.file, this.selectedConcesionarioId).subscribe(res => {
      this.route.navigate(['/vehiculos']);
    })
  }

  getAllMarcas(): void {
    this.marcaService.getMarcas().subscribe(data => {
      this.marcas = data.content;
    })
  }

}
