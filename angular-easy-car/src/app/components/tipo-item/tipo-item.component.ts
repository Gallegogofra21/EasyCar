import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Tipo } from 'src/app/models/tipo-interface';
import { TipoService } from 'src/app/services/tipo.service';

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
    private router: Router) { }

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

}
