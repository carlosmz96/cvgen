import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearCurriculumComponent } from './crear-curriculum.component';

describe('CrearCurriculumComponent', () => {
  let component: CrearCurriculumComponent;
  let fixture: ComponentFixture<CrearCurriculumComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CrearCurriculumComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CrearCurriculumComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
