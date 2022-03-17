import {ComponentFixture, TestBed} from '@angular/core/testing';

import {PostbusadresMatchDetailedComponent} from './postbusadres-match-detailed.component';

describe('PostbusadresMatchDetailedComponent', () => {
  let component: PostbusadresMatchDetailedComponent;
  let fixture: ComponentFixture<PostbusadresMatchDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PostbusadresMatchDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PostbusadresMatchDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
