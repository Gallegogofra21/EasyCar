import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Marca } from 'src/app/models/marca-interface';
import { MarcaService } from 'src/app/services/marca.service';

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
    private router: Router) { }

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

}
