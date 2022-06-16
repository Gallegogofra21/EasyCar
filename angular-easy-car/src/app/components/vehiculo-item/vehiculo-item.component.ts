import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Route } from '@angular/router';
import { Vehiculo } from 'src/app/models/vehiculo-interface';
import { VehiculoService } from 'src/app/services/vehiculo.service';
import { DialogEditVehiculoComponent } from '../dialog-edit-vehiculo/dialog-edit-vehiculo.component';

@Component({
  selector: 'app-vehiculo-item',
  templateUrl: './vehiculo-item.component.html',
  styleUrls: ['./vehiculo-item.component.css']
})
export class VehiculoItemComponent implements OnInit {
  @Input() vehiculoInput!: Vehiculo;
  id !: string;

  constructor(private vehiculoService: VehiculoService,
    private route: ActivatedRoute,
    private dialog: MatDialog) { }

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

  editVehiculo(vehiculo: Vehiculo, idVehiculo: number): void {
    this.dialog.open(DialogEditVehiculoComponent, {
      data: {vehiculo: vehiculo, id: idVehiculo},
      width: '500px',
      height: '500px',
    });
  }

}
