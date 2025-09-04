import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CurriculumDetailPageComponent } from './curriculum-detail-page.component';

describe('CurriculumDetailPageComponent', () => {
  let component: CurriculumDetailPageComponent;
  let fixture: ComponentFixture<CurriculumDetailPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CurriculumDetailPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CurriculumDetailPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
