import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalleCurriculumComponent } from './detalle-curriculum.component';

describe('DetalleCurriculumComponent', () => {
  let component: DetalleCurriculumComponent;
  let fixture: ComponentFixture<DetalleCurriculumComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetalleCurriculumComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetalleCurriculumComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
