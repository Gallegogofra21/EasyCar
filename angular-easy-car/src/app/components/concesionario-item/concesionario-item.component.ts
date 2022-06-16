import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Concesionario } from 'src/app/models/concesionario-interface';
import { ConcesionarioService } from 'src/app/services/concesionario.service';
import { DialogEditConcesionarioComponent } from '../dialog-edit-concesionario/dialog-edit-concesionario.component';

@Component({
  selector: 'app-concesionario-item',
  templateUrl: './concesionario-item.component.html',
  styleUrls: ['./concesionario-item.component.css']
})
export class ConcesionarioItemComponent implements OnInit {
  @Input() concesionarioInput!: Concesionario;
  id !: string;

  constructor(private concesionarioService: ConcesionarioService,
    private dialog: MatDialog) { }

  ngOnInit(): void {
  }

  getDeleteConcesionario(id: number | undefined) {
    if (id != null) {
      this.concesionarioService.deleteConcesionario(id).subscribe(result => {
        this.concesionarioInput = result;
        window.location.reload();
      })
    }
  }

  editConcesionario(concesionario: Concesionario, idConcesionario: number): void {
    this.dialog.open(DialogEditConcesionarioComponent, {
      data: {concesionario: concesionario, id: idConcesionario},
      width: '300px',
      height: '300px',
    });
  }

}
