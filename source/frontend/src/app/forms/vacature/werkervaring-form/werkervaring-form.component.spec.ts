import {ComponentFixture, TestBed} from '@angular/core/testing';

import {WerkervaringFormComponent} from './werkervaring-form.component';

describe('WerkervaringFormComponent', () => {
  let component: WerkervaringFormComponent;
  let fixture: ComponentFixture<WerkervaringFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WerkervaringFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WerkervaringFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
