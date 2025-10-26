export interface Experience {
  id?: number;
  position: string;
  company: string;
  location: string;
  startDate: Date;
  endDate?: Date;
  current: boolean;
  description?: string;
}
