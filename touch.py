import torch
from diffusers import StableDiffusionPipeline
from transformers import FSMTForConditionalGeneration, FSMTTokenizer
import time

st = time.time()

mname = "facebook/wmt19-de-en"
tokenizer = FSMTTokenizer.from_pretrained(mname)
model = FSMTForConditionalGeneration.from_pretrained(mname)
st2= time.time()

prompt = input("Gib mir deinen Input, Freund: ")

print("\nDein Input war auf deutsch: " + prompt + "\n")


et2 = time.time()
input_ids = tokenizer.encode(prompt, return_tensors="pt")

outputs = model.generate(input_ids)

decoded = tokenizer.decode(outputs[0], skip_special_tokens=True)

i = 0
while i < len(decoded)+33:
	print("-", end="")
	i = i + 1

print("\n This is your input translated: " + decoded) 

i = 0
while i < len(decoded)+33:
	print("-", end="")
	i = i + 1

et = time.time()

#et2 (Endtime 2) and st2 (Starttime 2) is the difference in time between user input and beginning of calculations
print("\nTime needed was: " + str(round((et - st) - (et2 - st2), 2)) + " seconds")

pipe = StableDiffusionPipeline.from_pretrained(
	"CompVis/stable-diffusion-v1-4",
	revision="fp16", 
	torch_dtype=torch.float16, 
	use_auth_token=True
).to("cuda")


with torch.cuda.amp.autocast(True):
    image = pipe(decoded)["sample"][0]  

print("\nSaved your file as: " + "f" + decoded + ".png")    
image.save("f" + decoded + ".png")
et = time.time()
print("\nTime needed complete was: " + str(round((et - st) - (et2 - st2), 2)) + " seconds") 
