import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewCurriculumComponent } from './new-curriculum.component';

describe('NewCurriculumComponent', () => {
  let component: NewCurriculumComponent;
  let fixture: ComponentFixture<NewCurriculumComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewCurriculumComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewCurriculumComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
