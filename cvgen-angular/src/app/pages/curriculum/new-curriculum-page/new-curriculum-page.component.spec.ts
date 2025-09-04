import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewCurriculumPageComponent } from './new-curriculum-page.component';

describe('NewCurriculumPageComponent', () => {
  let component: NewCurriculumPageComponent;
  let fixture: ComponentFixture<NewCurriculumPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewCurriculumPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewCurriculumPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
