import { Component, OnInit } from '@angular/core';
import { Vehiculo } from 'src/app/models/marca-details-interface';
import { VehiculoService } from 'src/app/services/vehiculo.service';

@Component({
  selector: 'app-vehiculo-list',
  templateUrl: './vehiculo-list.component.html',
  styleUrls: ['./vehiculo-list.component.css']
})
export class VehiculoListComponent implements OnInit {
  vehiculoList!: Vehiculo[];
  paginas!: number;

  constructor(private vehiculoService: VehiculoService) { }

  ngOnInit(): void {
    this.getVehiculos();
  }

  getVehiculos() {
    this.vehiculoService.getVehiculos().subscribe(res => {
      this.vehiculoList = res.content;
      this.paginas = res.totalPages;
    })
  }

}
