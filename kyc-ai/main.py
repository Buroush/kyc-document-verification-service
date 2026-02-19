from fastapi import FastAPI, File, UploadFile
import face_recognition
import numpy as np
from PIL import Image
import io

app = FastAPI()

@app.post("/compare")
async def compare_faces(
    id_image: UploadFile = File(...),
    selfie: UploadFile = File(...)
):
    try:
        id_bytes = await id_image.read()
        selfie_bytes = await selfie.read()

        id_img = face_recognition.load_image_file(io.BytesIO(id_bytes))
        selfie_img = face_recognition.load_image_file(io.BytesIO(selfie_bytes))

        id_encoding = face_recognition.face_encodings(id_img)
        selfie_encoding = face_recognition.face_encodings(selfie_img)

        if not id_encoding or not selfie_encoding:
            return {"error": "No face detected"}

        distance = np.linalg.norm(id_encoding[0] - selfie_encoding[0])
        similarity = float(1 - distance)

        verdict = "APPROVED" if similarity > 0.6 else "REJECTED"

        return {
            "confidenceScore": similarity,
            "verdict": verdict
        }

    except Exception as e:
        return {"error": str(e)}
