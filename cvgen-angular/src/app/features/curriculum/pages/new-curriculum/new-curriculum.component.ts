import { Component } from '@angular/core';
import { CountryService } from '../../../../services/country/country.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SelectModule } from 'primeng/select';

@Component({
  selector: 'app-new-curriculum',
  imports: [
    CommonModule,
    FormsModule,
    SelectModule
  ],
  templateUrl: './new-curriculum.component.html',
  styleUrl: './new-curriculum.component.scss'
})
export class NewCurriculumComponent {

  countries: string[] = [];
  selectedCountry: string = '';

  constructor(
    private countryService: CountryService
  ) {
    this.getCountries();
  }

  ngOnInit(): void {

  }

  private getCountries(): void {
    this.countryService.getCountries().subscribe(
      {
        next: (data: any[]) => {
          data.forEach(d => {
            this.countries.push(d.translations.spa.common);
          });

          this.countries.sort((a: string, b: string) => a.localeCompare(b))
        }
      }
    );
  }

}
