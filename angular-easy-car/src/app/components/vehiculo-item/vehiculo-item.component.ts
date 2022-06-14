import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Route } from '@angular/router';
import { Vehiculo } from 'src/app/models/marca-details-interface';
import { VehiculoService } from 'src/app/services/vehiculo.service';

@Component({
  selector: 'app-vehiculo-item',
  templateUrl: './vehiculo-item.component.html',
  styleUrls: ['./vehiculo-item.component.css']
})
export class VehiculoItemComponent implements OnInit {
  @Input() vehiculoInput!: Vehiculo;
  id !: string;

  constructor(private vehiculoService: VehiculoService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

  getDeleteVehiculo(id : number | undefined) {
    if (id != null) {
      this.vehiculoService.deleteVehiculo(id).subscribe(result => {
        this.vehiculoInput = result;
        window.location.reload();
      })
    }
  }

}
