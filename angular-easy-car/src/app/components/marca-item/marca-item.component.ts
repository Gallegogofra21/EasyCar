import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Marca } from 'src/app/models/marca-interface';
import { MarcaService } from 'src/app/services/marca.service';
import { DialogEditMarcaComponent } from '../dialog-edit-marca/dialog-edit-marca.component';

@Component({
  selector: 'app-marca-item',
  templateUrl: './marca-item.component.html',
  styleUrls: ['./marca-item.component.css']
})
export class MarcaItemComponent implements OnInit {
  @Input() marcaInput!: Marca;
  id !: string;

  constructor(private marcaService: MarcaService,
    private route: ActivatedRoute,
    private router: Router,
    private dialog: MatDialog) { }

  ngOnInit(): void {
  }

  getDeleteMarca(id : number | undefined) {
    if (id != null) {
      this.marcaService.deleteMarca(id).subscribe(result => {
        this.marcaInput = result;
        window.location.reload();
      })
    }
  }

  editMarca(marca: Marca, idMarca: number): void {
    this.dialog.open(DialogEditMarcaComponent, {
      data: {marca: marca, id: idMarca},
      width: '300px',
      height: '300px',
    });
  }

}
