export type Row = Record<string, any>;
export type Status = 'INTAKE'|'SURVEY'|'SCHEDULED'|'IN_PROGRESS'|'COMPLETED'|'INVOICED'|'CANCELLED';
const BASE = import.meta.env.VITE_API_BASE ?? '';
export async function request<T=Row>(path:string, credentials:string,method='GET',body?:unknown):Promise<T>{ const response=await fetch(`${BASE}/api/v1${path}`,{method,headers:{Authorization:`Basic ${btoa(credentials)}`, ...(body?{'Content-Type':'application/json'}:{})},body:body?JSON.stringify(body):undefined});if(!response.ok){let detail='Request failed';try{const p=await response.json();detail=p.detail??p.title??detail;}catch{} throw new Error(`${response.status}: ${detail}`);}return response.json();}
