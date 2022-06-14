import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Tipo, TipoResponse } from 'src/app/models/tipo-interface';
import { TipoService } from 'src/app/services/tipo.service';
import { DialogEditTipoComponent } from '../dialog-edit-tipo/dialog-edit-tipo.component';

@Component({
  selector: 'app-tipo-item',
  templateUrl: './tipo-item.component.html',
  styleUrls: ['./tipo-item.component.css']
})
export class TipoItemComponent implements OnInit {
  @Input() tipoInput!: Tipo;
  id !: string;

  constructor(private tipoService: TipoService,
    private route: ActivatedRoute,
    private router: Router,
    private dialog: MatDialog) { }

  ngOnInit(): void {
  }



  getDeleteTipo(id : number | undefined) {
    if (id != null) {
      this.tipoService.deleteTipo(id).subscribe(result => {
        this.tipoInput = result;
        window.location.reload();
      })
    }
  }

  editTipo(enterAnimationDuration: string, exitAnimationDuration: string, tipo: Tipo, idTipo: number): void {
    this.dialog.open(DialogEditTipoComponent, {
      data: {tipo: tipo, id: idTipo},
      width: '300px',
      height: '300px',
      enterAnimationDuration,
      exitAnimationDuration,
    });
  }

}
